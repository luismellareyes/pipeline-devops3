def call(){
    pipeline {
    agent any
    environment {
        NEXUS_USER         = credentials('nexus-user')
        NEXUS_PASSWORD     = credentials('nexus-password')
    }
    parameters {
        choice  name: 'compileTool', choices: ['Gradle', 'Maven'], description: 'Seleccione el empaquetador maven/gradle'
        string  name: 'stages', description: 'Ingrese los stages para ejecutar', trim: true
    }
    stages {
        stage("Pipeline"){
            steps {
                script{
                  switch(params.compileTool)
                    {
                        case 'Maven':
                             maven.call()
                        break;
                        case 'Gradle':
                             gradle.call()
                        break;
                    }
                }
            }
        }
    }
}
}