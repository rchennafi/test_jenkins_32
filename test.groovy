pipeline {
    agent { label 'master' }

    options {
        skipDefaultCheckout()
        timestamps()
    }

    stages {
        stage('print changes') {
            steps {
                echo currentBuild.changeSets
            }
        }

        stage('checkquit') {
            when { changeset "*"} {
                not {
                    steps {
                        if ("SUCCESS".equals(currentBuild.previousBuild.result)) {
                            echo "WOW!"
                            currentBuild.getRawBuild().getExecutor().interrupt(Result.SUCCESS)
                            sleep(1)
                        }
                }
            }
        }
    }
}
