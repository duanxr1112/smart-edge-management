node{
    try {
	    currentBuild.result = "Success"
	    sonar="Success"
        stage('clone') {
        git branch: 'developer', credentialsId: '00bc62c2-6967-4162-be1f-428abba5ef12', url: 'git@gitlab.lenovo.com:solution/btit/smartstore/smart-edge-management.git'
        }
        stage('SonarQube') {
	        dir('./'){
                echo "starting codeAnalyze with SonarQube......"
	            withSonarQubeEnv("sonar"){
	              sh "/var/jenkins_home/sonar-scanner/bin/sonar-scanner -Dproject.settings=./sonar-project.properties"
	            }
	            script {
	                timeout(10) { 
		                def qg = waitForQualityGate() 
	                    if (qg.status != 'OK') {								
			                sonar = "FAILED"
			                //error "未通过Sonarqube的代码质量阈检查，请及时修改！failure: ${qg.status}"
			            }
		           }	
	            }
	        }
	    }
        stage('Maven Build') {
		    dir('./') {
			    sh '/var/jenkins_home/apache-maven-3.6.1/bin/mvn clean package -Dmaven.test.skip=true'
			}
        } 
		
        stage('Image Build') {
            dir('./') {
                if(tag == 'dev'){
				docker.build("hub.cap.lenovo.com/aialive/smart-edge-management:dev").push()
				}else{
				docker.build("hub.cap.lenovo.com/aialive/smart-edge-management:$tag").push()
				}
			}
        }

        stage('deploy') {
            dir('./') {
                if(tag == 'dev'){
    				sh 'echo kubectl delete deployment'
    				sh 'sed -i "s|        image: hub.cap.lenovo.com/aialive/smart-edge-managemente:dev|        image: hub.cap.lenovo.com/aialive/smart-edge-management:$tag|" ./deployment/deploy-dev.yaml'
    				sh 'kubectl delete -f deployment/deploy-dev.yaml'
    				sh 'sleep 5'
    				sh 'echo kubectl apply deployment'
    				sh 'kubectl apply -f deployment/deploy-dev.yaml'
    				}else{
    				sh 'echo kubectl delete deployment'
    				sh 'sed -i "s|        image: hub.cap.lenovo.com/aialive/smart-edge-management:test|        image: hub.cap.lenovo.com/aialive/smart-edge-management:$tag|" ./deployment/deploy-test.yaml'
    				sh 'kubectl delete -f deployment/deploy-test.yaml'
    				sh 'sleep 5'
    				sh 'echo kubectl apply deployment'
    				sh 'kubectl apply -f deployment/deploy-test.yaml'
    				}
			}
        }
	
	}catch (e) {
		// If there was an exception thrown, the build failed
		currentBuild.result = "FAILED"
		throw e
		}finally {
		// Success or failure, always send notifications
		sendEmail(currentBuild.result,sonar)
		}
}
def sendEmail(status,sonar) {
	emailext body: 
		'''
		<!DOCTYPE html>  
		<html>  
		<head>  
		<meta charset="UTF-8">  
		<title>${ENV, var="JOB_NAME"}-第${BUILD_NUMBER}次构建日志</title>  
		</head>  
		  
		<body leftmargin="8" marginwidth="0" topmargin="8" marginheight="4"  
			offset="0">  
			<table width="95%" cellpadding="0" cellspacing="0"  style="font-size: 11pt; font-family: Tahoma, Arial, Helvetica, sans-serif">  
				<tr>  
					<td>各位同事，大家好，以下为${PROJECT_NAME }项目构建信息</td>  
				</tr>  
				<tr>  
					<td><br />  
					<b><font color="#0B610B">构建信息</font></b>  
					<hr size="2" width="100%" align="center" /></td>  
				</tr>  
				<tr>  
					<td>  
						<ul>  
							<li>项目名称 ：status</li>
							<li>项目名称 ： ${PROJECT_NAME}</li>  
							<li>构建编号 ： 第${BUILD_NUMBER}次构建</li>  
							<li><font color="#0B610B">是否通过sonar检测 ：'''+sonar+'''</font></li> 
							<li>sonar检测信息URL: <a href=http://10.110.156.67:30201/dashboard?id=${PROJECT_NAME}>http://10.110.156.67:30201/dashboard?id=${PROJECT_NAME}</a></li>
							<li>触发原因： ${CAUSE}</li>  
							<li>构建状态： ${BUILD_STATUS}</li>  
							<li>构建日志： <a href="${BUILD_URL}console">${BUILD_URL}console</a></li>  
							<li>构建  Url ： <a href="${BUILD_URL}">${BUILD_URL}</a></li>  
							<li>工作目录 ： <a href="${PROJECT_URL}ws">${PROJECT_URL}ws</a></li>  
							<li>项目  Url ： <a href="${PROJECT_URL}">${PROJECT_URL}</a></li>
							//<li>构建日志 ： ${BUILD_LOG}</li>
						</ul>  
					</td>  
				</tr>  
			</table>  
		</body>  
		</html>  
		''', 
		recipientProviders: [[$class: 'DevelopersRecipientProvider']], 
		subject: '【构建' + status + '通知】$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!', 
		to: 'dengxh2@lenovo.com,yinxy4@lenovo.com,duanxr2@lenovo.com'
}
