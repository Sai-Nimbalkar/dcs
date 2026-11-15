terminal1: 
set path temp: $env:Path += ";C:\apache-maven-3.10.0\bin"
set java version: $jdk = Get-ChildItem "C:\Program Files\Eclipse Adoptium" -Directory |
         Where-Object { $_.Name -like "jdk-17*" } |  
         Select-Object -First 1  
    
 $env:JAVA_HOME = $jdk.FullName                                             
 $env:Path = "$env:JAVA_HOME\bin;$env:Path"  
mvn clean compile

terminal 2:
set path temp: $env:Path += ";C:\apache-maven-3.10.0\bin"
set java version: $jdk = Get-ChildItem "C:\Program Files\Eclipse Adoptium" -Directory |
         Where-Object { $_.Name -like "jdk-17*" } |  
         Select-Object -First 1  
    
 $env:JAVA_HOME = $jdk.FullName                                             
 $env:Path = "$env:JAVA_HOME\bin;$env:Path" 

 mvn exec:java "-Dexec.mainClass=com.example.MessageBroker"


terminal 3:
set path temp: $env:Path += ";C:\apache-maven-3.10.0\bin"
set java version: $jdk = Get-ChildItem "C:\Program Files\Eclipse Adoptium" -Directory |
         Where-Object { $_.Name -like "jdk-17*" } |  
         Select-Object -First 1  
    
 $env:JAVA_HOME = $jdk.FullName                                             
 $env:Path = "$env:JAVA_HOME\bin;$env:Path" 

mvn exec:java "-Dexec.mainClass=com.example.Subscriber"


terminal 4:
mvn exec:java "-Dexec.mainClass=com.example.Publisher"

link to install maven: https://maven.apache.org/download.cgi?utm_source=chatgpt.com