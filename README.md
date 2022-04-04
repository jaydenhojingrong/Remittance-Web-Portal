# Tech-G Remittance Web Portal 

<!-- ![Notus React](https://github.com/creativetimofficial/public-assets/blob/master/notus-react/notus-react.jpg?raw=true) -->

Sample image?

Sample Description text

## Table of Contents

* [Documentation and Slides](#documentation-and-slides)
* [Get Started](#get-started)
* [Pages](#pages)
* [Files and folders](#files-and-folders)
* [Browser Support](#browser-support)
* [Reporting Issues](#reporting-issues)
* [Useful Links](#useful-links)
* [Resources](#resources)


## Documentation and Slides
- The documentation for the Website is listed in this [Google Document] (https://docs.google.com/document/d/1TH-2T7WjOwWoIifjMQuwx-XUwoR1D0_0xwM2nWQGlFM/edit?usp=sharing)
- The presentation slides for the Website is listed in this [Google Slide](https://docs.google.com/presentation/d/1cmd2PcAx-LY5UmClwsrdcRgjJUuaU0WDT5JM4I3Yky4/edit?usp=sharing)

## Get Started

- Ensure that mavan is installed and put in environment PATH: https://www.tutorialspoint.com/maven/maven_environment_setup.html
- Ensure that ;atest version of node.js is installed: https://nodejs.org/en/
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

## Pages

Here are all the page route from the project:
- Routes for Dashboard and functions
  - Dashboard http://localhost:3000/admin/dashboard
  - Upload http://localhost:3000/upload/uploadfile
  - Mapping http://localhost:3000/admin/mapping
- Routes for Authentication 
  - Login: http://localhost:3000/auth/login
  - Register: http://localhost:3000/auth/register


## CSS Components

Notus React comes with 120 Fully Coded CSS elements, such as [Alerts](https://www.creative-tim.com/learning-lab/tailwind/react/alerts/notus?ref=nr-github-readme), [Buttons](https://www.creative-tim.com/learning-lab/tailwind/react/buttons/notus?ref=nr-github-readme), [Inputs](https://www.creative-tim.com/learning-lab/tailwind/react/inputs/notus?ref=nr-github-readme) and many more.

Please [check all of them here](https://www.creative-tim.com/learning-lab/tailwind/react/alerts/notus?ref=nr-github-readme).

## React Components

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


## Useful Links

- <a href="https://www.youtube.com/channel/UCVyTG4sCw-rOvB9oHkzZD1w" target="_blank">Tutorials</a>
- <a href="https://www.creative-tim.com/affiliates/new?ref=nr-readme" target="_blank">Affiliate Program</a> (earn money)
- <a href="http://blog.creative-tim.com/?ref=nr-readme" target="_blank">Blog Creative Tim</a>
- <a href="https://www.creative-tim.com/templates/free?ref=nr-readme" target="_blank">Free Products</a> from Creative Tim
- <a href="https://www.creative-tim.com/templates/premium?ref=nr-readme" target="_blank">Premium Products</a> from Creative Tim
- <a href="https://www.creative-tim.com/templates/react?ref=nr-readme" target="_blank">React Products</a> from Creative Tim
- <a href="https://www.creative-tim.com/templates/angular?ref=nr-readme" target="_blank">Angular Products</a> from Creative Tim
- <a href="https://www.creative-tim.com/templates/vuejs?ref=nr-readme" target="_blank">VueJS Products</a> from Creative Tim
- <a href="https://www.creative-tim.com/templates?ref=nr-readme" target="_blank">More products</a> from Creative Tim
- Check our Bundles <a href="https://www.creative-tim.com/bundles?ref=nr-readme" target="_blank">here</a>
- Check our awesome builder <a href="https://www.creative-tim.com/builder/argon?ref=nr-readme" target="_blank">here</a>
- Check Tailwind Starter Kit, the project behind this product <a href="https://www.creative-tim.com/learning-lab/tailwind-starter-kit/presentation?ref=nr-readme" target="_blank">here</a>
<!-- 
### Social Media

Twitter: <a href="https://twitter.com/CreativeTim" target="_blank">https://twitter.com/CreativeTim</a>

Facebook: <a href="https://www.facebook.com/CreativeTim" target="_blank">https://www.facebook.com/CreativeTim</a>

Dribbble: <a href="https://dribbble.com/creativetim" target="_blank">https://dribbble.com/creativetim</a>

Instagram: <a href="https://www.instagram.com/creativetimofficial/" target="_blank">https://www.instagram.com/creativetimofficial/</a> -->


## Resources
- Demo: <a href="https://demos.creative-tim.com/notus-react/?ref=nr-readme" target="_blank">https://demos.creative-tim.com/notus-react/?ref=nr-readme</a>
- Download Page: <a href="https://www.creative-tim.com/product/notus-react?ref=nr-github-readme" target="_blank">https://www.creative-tim.com/product/notus-react</a>
- Documentation: <a href="https://www.creative-tim.com/learning-lab/tailwind/react/overview/notus?ref=nr-readme" target="_blank">https://www.creative-tim.com/learning-lab/tailwind/react/overview/notus?ref=nr-readme</a>
- License Agreement: <a href="https://www.creative-tim.com/license?ref=nr-readme" target="_blank">https://www.creative-tim.com/license?ref=nr-readme</a>
- Support: <a href="https://www.creative-tim.com/contact-us?ref=nr-readme" target="_blank">https://www.creative-tim.com/contact-us?ref=nr-readme</a>
- Issues: <a href="https://github.com/creativetimofficial/notus-react/issues" target="_blank">Github Issues Page</a>
