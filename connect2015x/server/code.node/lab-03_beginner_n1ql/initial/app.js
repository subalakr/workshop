var couchbase = require('couchbase');
var express = require('express');
var uuid = require('uuid');
var app = express();

var cluster = new couchbase.Cluster('couchbase://localhost');
var bucket = cluster.openBucket('travel-sample');

app.get('/flightpath/all', function (req, resp) {

  // TODO: implement me

  resp.send('');
});

app.get('/airport/allNames', function(req, resp) {

  // TODO: implement me

  resp.send('');
});

app.get('/airport/prefixedBy', function(req, resp) {

  // TODO: implement me

  resp.send('');
});

app.get('/airport/byFaa', function(req, resp) {

  // TODO: implement me

  resp.send('');
});

app.get('/airport/routes', function(req, resp) {

  // TODO: implement me

  resp.send('');
});

var server = app.listen(4300, function () {
  var host = server.address().address;
  var port = server.address().port;
  console.log('Example app listening at http://%s:%s', host, port);
});
