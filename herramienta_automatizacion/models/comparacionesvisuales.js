// app/models/regresionvisual.js

var mongoose     = require('mongoose');
var Schema       = mongoose.Schema;

var InformeSchema   = new Schema({
    imagen1: String,
    imagen2: String,
    rutaImagenComparacion: String,
    informacion: String,
    fechaCreacion: { type: Date, default: Date.now }
});

module.exports = mongoose.model('Informe', InformeSchema);
