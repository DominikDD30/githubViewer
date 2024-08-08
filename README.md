## Github Viewer
This is simple application to check user repositories on Gihtub

## Quick Demo


https://github.com/user-attachments/assets/590924bf-7f4d-4c44-a2d8-311d4798a5fe


## Installation
1. Clone the repository
2. open cloned directory in your IDE
3. run application
4. go to http://localhost:8080/viewer/swagger-ui/index.html
   
## Docker Installation
1. Clone the repository to your directory
2. open cloned directory in terminal (path should end with \githubViewer)
3. type docker build -t github-viewer .
4. next type docker run -d --name githubc -p 8080:8080 github-viewer
5. go to http://localhost:8080/viewer/swagger-ui/index.html
