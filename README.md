## 简单概述
  小组毕业管理系统-社团管理系统 

   使用SpringCloud作为后端设计  
   Vue+WebPack作为Web端  
   Mui作为移动APP端。      
       
      
   coreApp：核心的模块，包含社团的所有功能。  
   mass_app：移动APP  
   mass_commom：后端基本依赖  
   mass_eureka：后端注册中心  
   mass_frontend：Web应用  
   mass_zuul：后端网关  
   userApp：用户和权限管理  


# 后端运行

  顺序：  
   mass_eureka  
   userApp  
   coreApp  
   mass_zuul
   
  打包:
  mvn clean package -DskipTests -Pqa
  
  请注意各项目中的pom配置。
 
# web运行

 `yarn `  
 `yarn dev`  
 打包
 `yarn build`
 
 
# app运行

  需要Hbuild
 
   
   