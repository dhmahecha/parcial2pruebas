// server.js

// BASE SETUP
// =============================================================================

// call the packages we need
var exec = require('child_process').exec, child;
var Reportes    = require('./models/reportes');       
var express    = require('express');        // call express
var app        = express();                 // define our app using express
var bodyParser = require('body-parser');
var shell = require('shelljs');
var exec = require('exec-then');
const compare = require('resemblejs').compare;
var async = require('async');
const fs = require("mz/fs");
const uuidv4 = require('uuid/v4');
const compareImages = require('resemblejs/compareImages');



// configure app to use bodyParser()
// this will let us get the data from a POST
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());


var ComparacionesVisuales 	= require('./models/comparacionesvisuales'); 

const rutaImagenes = 'public/imagenes/';


var port = process.env.PORT || 8080;        // set our port

// ROUTES FOR OUR API
// =============================================================================
var router = express.Router();              // get an instance of the express Router

// middleware to use for all requests
router.use(function(req, res, next) {
    // do logging
    console.log('Something is happening.');
    next(); // make sure we go to the next routes and don't stop here
});

// test route to make sure everything is working (accessed at GET http://localhost:8080/api)
router.get('/', function(req, res) {
    res.sendfile(__dirname + '/index.html');	
    //res.json({ message: 'Bienvenido al api rest' });   
});

router.get('/api', function(req, res) {
    res.json({ message: 'Bienvenido al api rest' });   
});


router.route('/ejecutarPruebas')
	.post(function(req, res) {
		var rutaSDK = "/Users/Diego/Library/Android/sdk/";
		var rutaAPKs = "/Users/Diego/Downloads/apks/";
		var comandoInstalarAplicacion = "adb -s emulator-5554 install -r";
		var rutaProyectoPruebas = "/Users/Diego/PycharmProjects/CarReportsrc/";
		var comandoLanzarPruebas = "./gradlew connectedCheck";
		var nombreAplicacionFirmada = "signed-carreport.apk";
		var paqueteAdb = "platform-tools/";
		var SUCCESS = "Success";
		var comandoEmulador = "/Users/Diego/Library/Android/sdk/tools/emulator -avd Nexus_5X_API_26";

		//Users/Diego/Library/Android/sdk/platform-tools/adb -s emulator-5554 install -r /Users/Diego/Downloads/apks/apk5/signed-carreport.apk
		var i = 2;
		var apks = [];

		banderaFor = false;

		for(i=2;i<=100;i++){
			apks[i]=i;
		}

		/*exec(comandoEmulador)
		.then(function(res) {
		});	*/
			
		shell.exec(comandoEmulador);
			apks.forEach(function(i){
			exec(rutaSDK + paqueteAdb + comandoInstalarAplicacion + " " + rutaAPKs + "apk" + i + "/" + nombreAplicacionFirmada)
				.then(function(res) {
					console.log(res.stdout);
					if(res.stdout.localeCompare(SUCCESS)){
						shell.cd(rutaProyectoPruebas);
						/*exec(comandoLanzarPruebas)
						.then(function(res) {
							console.log(res);
						});*/	
						shell.exec(comandoLanzarPruebas);
					}
				}, 
				function(err) {
					console.log(err);
				});	
			});	
			banderaFor = true;

		if(banderaFor){
			console.log("Pruebas Enviadas");
		}
		res.json({ message: 'Pruebas Enviadas' }); 

	});	



// more routes for our API will happen here
app.use(function(req, res, next) {
    res.header('Access-Control-Allow-Origin', "*");
    res.header('Access-Control-Allow-Methods','GET,PUT,POST,DELETE');
    res.header('Access-Control-Allow-Headers', 'Content-Type');
    next();
})

// REGISTER OUR ROUTES -------------------------------
// all of our routes will be prefixed with /api
app.use('/', router);
app.use('/api', router);
app.use('/public', express.static(__dirname + '/public'));
app.use('/static', express.static(__dirname + '/node_modules'));


// START THE SERVER
// =============================================================================
app.listen(port);
console.log('Su servidor esta corriendo por el puerto ' + port);
