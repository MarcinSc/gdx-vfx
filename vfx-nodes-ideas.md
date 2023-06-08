### Field types:

- Process
- Boolean
- ParticleProcess (extends Process)
- KineticParticleProcess (extends ParticleProcess)
- Float
- Vector2
- Vector3
- SpriteOutput
- PatchOutput (extends SpriteOutput)
- Model

### Rendering:

- SpriteRendering:
    - inputs: 
        - (Sprite) input,
    - fields:
        - tag - GraphShader tag
        - shape - used to extract from config,
        - drop-down from registered for preview (Preview shape and uniforms)
- PatchRendering:
    - inputs:
        - (Patch) input,
    - fields:
        - tag - GraphShader tag
- ModelRendering:
    - inputs:
        - (Model) input,
    - fields:
        - tag - GraphShader tag

### Main nodes:

- ParticleEmitter:
    - inputs:
        - (Boolean) Run,
        - (Float) Init #,
        - (Float) PerSecond,
        - (Float) Lifetime
    - outputs:
        - (SpriteOutput) Output,
        - (ParticleProcess) Particle init,
        - (ParticleProcess) Particle update,
        - (ParticleProcess) Emitter stop
- KineticParticleEmitter:
    - inputs:
        - (Boolean) Run,
        - (Float) Init #,
        - (Float) PerSecond,
        - (Float) Lifetime
    - outputs:
        - (SpriteOutput) Output,
        - (KineticParticleProcess) Particle init,
        - (KineticParticleProcess) Particle update,
        - (KineticParticleProcess) Emitter stop

### Processing:
- SetPosition:
    - inputs:
        - ParticleProcess - particle
        - Vector3
    - outputs:
        - ParticleProcess - particle
- MovePosition:
    - inputs:
        - ParticleProcess - particle
        - Vector3
    - outputs:
        - ParticleProcess - particle
- SetProperty:
    - inputs:
        - ParticleProcess - particle
        - Object - value
    - outputs:
        - ParticleProcess - particle
    - fields:
        - property name
- ResetProperty:
    - inputs:
        - ParticleProcess - particle
    - outputs:
        - ParticleProcess - particle
    - fields:
        - property name
- SetSpeed:
    - inputs:
        - KineticParticleProcess - particle
        - Vector3
    - outputs:
        - KineticParticleProcess - particle
- AddSpeed:
    - inputs:
        - KineticParticleProcess - particle
        - Vector3
    - outputs:
        - KineticParticleProcess - particle
- SetAcceleration:
    - inputs:
        - KineticParticleProcess - particle
        - Vector3
    - outputs:
        - KineticParticleProcess - particle
- AddAcceleration:
    - inputs:
        - KineticParticleProcess - particle
        - Vector3
    - outputs:
        - KineticParticleProcess - particle

### Triggers:

- Start:
    - outputs:
        - (Process) output
- Time:
    - outputs:
        - (Process) output,
    - fields:
        - seconds float input,
- Event:  - (Process) output
    - outputs:
        - (Process) output,
    - fields:
        - event name (called from application)

### Miscellaneous:

- Latch:
    - inputs:
        - (Process) Set,
        - (Process) Reset
    - outputs:
        - (Boolean) output
