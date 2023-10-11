pipeline {
    agent { label 'master' }

    options {
        timestamps()
    }

    stages {
        stage('print changes') {
            steps {
                script {
                    def changeLogSets = currentBuild.changeSets
                    echo("changeSets=" + changeLogSets)
                    for (int i = 0; i < changeLogSets.size(); i++) {
                        def entries = changeLogSets[i].items
                        for (int j = 0; j < entries.length; j++) {
                            def entry = entries[j]
                            echo "${entry.commitId} by ${entry.author} on ${new Date(entry.timestamp)}: ${entry.msg}"
                            def files = new ArrayList(entry.affectedFiles)
                            for (int k = 0; k < files.size(); k++) {
                                def file = files[k]
                                echo " ${file.editType.name} ${file.path}"
                            }
                        }
                    }
                }
            }
        }

        stage('checkquit') {
            when {
                not {
                    changeset "*"
                }
            }

            steps {
                echo "I QUIT!"
                script {
                    currentBuild.getRawBuild().getExecutor().interrupt(Result.SUCCESS)
                    sleep(1)
                }
            }
        }
    }
}
