pipeline {
    agent { label 'master' }

    options {
        skipDefaultCheckout()
        timestamps()
    }

    stages {
        stage('checkquit') {
            when { changeset "*"} {
                not {
                    steps {
                        if ("SUCCESS".equals(currentBuild.previousBuild.result)) {
                            echo "WOW!"
                            currentBuild.getRawBuild().getExecutor().interrupt(Result.SUCCESS)
                            sleep(1)   // Interrupt is not blocking and does not take effect immediately.
                        } else {
                            echo "NO!"
                        }
                    }
                }
            }
        }
    }
}
