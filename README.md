# Tech-G Remittance Web Portal 

<!-- ![Notus React](https://github.com/creativetimofficial/public-assets/blob/master/notus-react/notus-react.jpg?raw=true) -->
A minimalistic web page constructed using React, Springboot and SQL that automatically map fields to one another based on a Single Source of Truth that is fully customizable and easy to use. 


## Table of Contents

* [Screenshots](#screenshots)
* [Documentation and Slides](#documentation-and-slides)
* [How to start up the system](#how-to-start-up-the-system)
* [Pages and routes](#pages-and-routes)
* [Files and Folder](#files-and-folder)
* [References](#references)


## Screenshots:

### Dashboard
![Dashboard](https://github.com/jaydenhojingrong/Remittance-Web-Portal/blob/main/public/dashboard.PNG)
<br/>
### Mapping page
![Mapping](https://github.com/jaydenhojingrong/Remittance-Web-Portal/blob/main/public/mapping.PNG)


## Documentation and Slides
- The presentation slides for the Website is listed in this [Google Slide](https://docs.google.com/presentation/d/1TeKF7JN_-i3zOv3ahheOMmAOA_8OY6CHfI-vNN4A_ug/edit?usp=sharing)

## How to start up the system

- import the [SQL script](https://github.com/jaydenhojingrong/Remittance-Web-Portal/blob/main/db_script/remittancedb.sql) into your localhost / server 
- Ensure that mavan is installed and put in environment PATH: https://www.tutorialspoint.com/maven/maven_environment_setup.html
- Ensure that latest version of node.js is installed: https://nodejs.org/en/
- Unzip the downloaded file to a folder in your computer
- Open Terminal <br /> <br />
  **FRONTEND**: 
- Go to your file project (where you’ve unzipped the product)
- (If you are on a linux based terminal) run `npm run install:clean`
- (If not) Run in terminal `npm install`
- (If not) Run in terminal `npm start`
- Navigate to https://localhost:3000 <br /> <br />
  **BACKEND**:
- Go to your file project (where you’ve unzipped the product)
- Run in terminal mvn spring-boot:run

## Pages and routes

Here are all the page route from the project:
- Routes for Dashboard and functions
  - Dashboard http://localhost:3000/admin/dashboard
  - Upload http://localhost:3000/upload/uploadfile
  - Mapping http://localhost:3000/admin/mapping
- Routes for Authentication 
  - Login: http://localhost:3000/auth/login
  - Register: http://localhost:3000/auth/register


## Files and Folder

This is the project structure: (dependencies excluded)
```
Remittance-Web-Portal
├───src
│    ├───assets
│    │   ├───img
│    │   └───styles
│    ├───components (react frontend components)
│    │   ├───Cards
│    │   ├───Dropdowns
│    │   ├───Footers
│    │   ├───Headers
│    │   ├───Mapping
│    │   ├───Navbars
│    │   ├───Playground
│    │   └───Sidebar
│    ├───layouts
│    ├───main (backend main server)
│    │   ├───java
│    │   │   └───com
│    │   │       └───OOP
│    │   │           └───remittancesystem
│    │   │               ├───controller
│    │   │               ├───dao
│    │   │               ├───entity
│    │   │               ├───exception
│    │   │               ├───fileHandling
│    │   │               └───service
│    │   └───resources
│    │       └───service
│    │───test (backend test server)
│    │   └───java
│    └───views  (react frontend pages )
│        ├───admin
│        ├───auth
│        └───upload
└───target (backend compiled java files)
    └───classes
        └───com
            └───OOP
                └───remittancesystem
                    ├───controller
                    ├───dao
                    ├───entity
                    ├───exception
                    ├───fileHandling
                    └───service    

```
## References
- Demo: <a href="https://demos.creative-tim.com/notus-react/?ref=nr-readme" target="_blank">https://demos.creative-tim.com/notus-react/?ref=nr-readme</a>
- Download Page: <a href="https://www.creative-tim.com/product/notus-react?ref=nr-github-readme" target="_blank">https://www.creative-tim.com/product/notus-react</a>
- Documentation: <a href="https://www.creative-tim.com/learning-lab/tailwind/react/overview/notus?ref=nr-readme" target="_blank">https://www.creative-tim.com/learning-lab/tailwind/react/overview/notus?ref=nr-readme</a>
- License Agreement: <a href="https://www.creative-tim.com/license?ref=nr-readme" target="_blank">https://www.creative-tim.com/license?ref=nr-readme</a>
- Support: <a href="https://www.creative-tim.com/contact-us?ref=nr-readme" target="_blank">https://www.creative-tim.com/contact-us?ref=nr-readme</a>
- Issues: <a href="https://github.com/creativetimofficial/notus-react/issues" target="_blank">Github Issues Page</a>
