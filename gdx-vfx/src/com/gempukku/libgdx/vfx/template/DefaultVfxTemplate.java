package com.gempukku.libgdx.vfx.template;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.graph.data.GraphWithProperties;
import com.gempukku.libgdx.ui.graph.data.*;
import com.gempukku.libgdx.vfx.*;
import com.gempukku.libgdx.vfx.node.VfxRuntimeNode;

public class DefaultVfxTemplate implements VfxTemplate {
    private final GraphWithProperties graph;
    private final boolean designTime;

    public DefaultVfxTemplate(GraphWithProperties graph, boolean designTime) {
        this.graph = graph;
        this.designTime = designTime;
    }

    @Override
    public VfxInstance createEffect(VfxEffectConfiguration configuration) {
        ObjectMap<String, VfxRuntimeNode> nodes = new ObjectMap<>();
        createVfxNodes(configuration, nodes);
        initializeVfxNodes(nodes);

        return new DefaultVfxInstance(nodes.values().toArray(), configuration);
    }

    private void initializeVfxNodes(ObjectMap<String, VfxRuntimeNode> nodes) {
        for (ObjectMap.Entry<String, VfxRuntimeNode> vfxNode : nodes) {
            GraphNode node = graph.getNodeById(vfxNode.key);

            VfxRuntimeNodeProducer producer = VfxGraphConfiguration.findProducer(node.getType());
            if (producer == null && !canIgnoreMissing(node))
                throw new GdxRuntimeException("Unable to find Vfx node producer for type: " + node.getType());

            NodeConfiguration nodeConfiguration = producer.getConfiguration(node.getData());

            ObjectMap<String, Array<VfxRuntimeNode.RelationshipConnection>> inputNodes = createInputRelationships(nodes, vfxNode, nodeConfiguration);

            ObjectMap<String, Array<VfxRuntimeNode.RelationshipConnection>> outputNodes = createOutputRelationships(nodes, vfxNode, nodeConfiguration);

            vfxNode.value.initializeRelationships(inputNodes, outputNodes);
        }
    }

    private boolean canIgnoreMissing(GraphNode node) {
        return node.getData() != null && node.getData().getBoolean("ignoreMissing", false);
    }

    private ObjectMap<String, Array<VfxRuntimeNode.RelationshipConnection>> createOutputRelationships(ObjectMap<String, VfxRuntimeNode> nodes, ObjectMap.Entry<String, VfxRuntimeNode> vfxNode, NodeConfiguration nodeConfiguration) {
        ObjectMap<String, Array<VfxRuntimeNode.RelationshipConnection>> outputNodes = new ObjectMap<>();
        for (GraphNodeOutput graphNodeOutput : new ObjectMap.Values<>(nodeConfiguration.getNodeOutputs())) {
            Array<VfxRuntimeNode.RelationshipConnection> fieldConnections = new Array<>();
            for (GraphConnection outputConnection : findOutputConnections(vfxNode.key, graphNodeOutput.getFieldId())) {
                fieldConnections.add(new VfxRuntimeNode.RelationshipConnection(outputConnection.getFieldTo(), nodes.get(outputConnection.getNodeTo())));
            }
            outputNodes.put(graphNodeOutput.getFieldId(), fieldConnections);
        }
        return outputNodes;
    }

    private ObjectMap<String, Array<VfxRuntimeNode.RelationshipConnection>> createInputRelationships(ObjectMap<String, VfxRuntimeNode> nodes, ObjectMap.Entry<String, VfxRuntimeNode> vfxNode, NodeConfiguration nodeConfiguration) {
        ObjectMap<String, Array<VfxRuntimeNode.RelationshipConnection>> inputNodes = new ObjectMap<>();
        for (GraphNodeInput graphNodeInput : new ObjectMap.Values<>(nodeConfiguration.getNodeInputs())) {
            Array<VfxRuntimeNode.RelationshipConnection> fieldConnections = new Array<>();
            for (GraphConnection inputConnection : findInputConnections(vfxNode.key, graphNodeInput.getFieldId())) {
                fieldConnections.add(new VfxRuntimeNode.RelationshipConnection(inputConnection.getFieldFrom(), nodes.get(inputConnection.getNodeFrom())));
            }
            inputNodes.put(graphNodeInput.getFieldId(), fieldConnections);
        }
        return inputNodes;
    }

    private void createVfxNodes(VfxEffectConfiguration configuration, ObjectMap<String, VfxRuntimeNode> nodes) {
        ObjectMap<String, ObjectMap<String, String>> nodeOutputTypes = new ObjectMap<>();

        for (GraphNode node : graph.getNodes()) {
            String nodeId = node.getId();
            populateVfxNode(nodeId, nodes, nodeOutputTypes, configuration);
        }
    }

    private void populateVfxNode(String nodeId, ObjectMap<String, VfxRuntimeNode> nodes, ObjectMap<String, ObjectMap<String, String>> nodeOutputTypes, VfxEffectConfiguration configuration) {
        if (!nodes.containsKey(nodeId)) {
            GraphNode node = graph.getNodeById(nodeId);

            VfxRuntimeNodeProducer producer = VfxGraphConfiguration.findProducer(node.getType());
            if (producer == null)
                throw new GdxRuntimeException("Unable to find Vfx node producer for type: " + node.getType());

            NodeConfiguration nodeConfiguration = producer.getConfiguration(node.getData());

            ObjectMap<String, Array<String>> inputTypes = new ObjectMap<>();
            for (GraphNodeInput graphNodeInput : new ObjectMap.Values<>(nodeConfiguration.getNodeInputs())) {
                Array<String> inputFieldTypes = new Array<>();
                String fieldId = graphNodeInput.getFieldId();
                Array<GraphConnection> inputConnections = findInputConnections(nodeId, fieldId);
                for (GraphConnection inputConnection : inputConnections) {
                    populateVfxNode(inputConnection.getNodeFrom(), nodes, nodeOutputTypes, configuration);
                    String fieldType = nodeOutputTypes.get(inputConnection.getNodeFrom()).get(inputConnection.getFieldFrom());
                    inputFieldTypes.add(fieldType);
                }
                inputTypes.put(fieldId, inputFieldTypes);
            }

            ObjectMap<String, String> outputTypes = new ObjectMap<>();
            for (GraphNodeOutput graphNodeOutput : new ObjectMap.Values<>(nodeConfiguration.getNodeOutputs())) {
                String fieldId = graphNodeOutput.getFieldId();
                Array<GraphConnection> outputConnections = findOutputConnections(nodeId, fieldId);
                if (outputConnections.size > 0) {
                    String fieldType = graphNodeOutput.determineFieldType(inputTypes);
                    outputTypes.put(fieldId, fieldType);
                }
            }
            nodeOutputTypes.put(nodeId, outputTypes);

            VfxRuntimeNode vfxNode = producer.createNode(node.getData(), inputTypes, outputTypes, configuration);
            nodes.put(nodeId, vfxNode);
        }
    }

    private Array<GraphConnection> findInputConnections(String nodeId, String nodeField) {
        Array<GraphConnection> result = new Array<>();
        for (GraphConnection vertex : graph.getConnections()) {
            if (vertex.getNodeTo().equals(nodeId) && vertex.getFieldTo().equals(nodeField))
                result.add(vertex);
        }
        return result;
    }

    private Array<GraphConnection> findOutputConnections(String nodeId, String nodeField) {
        Array<GraphConnection> result = new Array<>();
        for (GraphConnection vertex : graph.getConnections()) {
            if (vertex.getNodeFrom().equals(nodeId) && vertex.getFieldFrom().equals(nodeField))
                result.add(vertex);
        }
        return result;
    }


}
