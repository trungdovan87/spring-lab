- install notejs, npm
- create folder react-demo
- cd react-demo
- npm init, setup dafault
- run command to install (from scratch):
	+ $ npm install react react-dom --save
	+ $ npm install webpack webpack-dev-server babel-core babel-loader babel-preset-es2015 babel-preset-react babel-preset-stage-2 --save-dev

- if have "package.json" run: npm install

- tree folder:
	src
		-app
			-index.js
		-index.html


- copy file webpack.config.js to react-demo
- choose package.json, replace "test" by 
	"start": "npm run build",
        "build": "webpack -d && cp src/index.html dist/index.html && webpack-dev-server --content-base src/ --inline --hot",
        "build:prod": "webpack -d && cp src/index.html dist/index.html"

- run app: npm start
