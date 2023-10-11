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
                        }
                    }
                }
            }
        }
    }
}
