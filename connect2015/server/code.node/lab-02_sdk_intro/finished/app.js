var couchbase = require('couchbase');
var express = require('express');
var uuid = require('uuid');
var app = express();

var cluster = new couchbase.Cluster('couchbase://localhost');
var bucket = cluster.openBucket('default');

app.get('/hello', function (req, resp) {
  var id = 'user::' + uuid.v4();
  bucket.insert(id, {
    type: 'user',
    hello: 'world',
    time: new Date()
  }, function(err, res) {
    bucket.get(id, function(err, res) {
      resp.send(res.value);
    });
  });
});

app.get('/world', function(req, resp) {
  var q = couchbase.N1qlQuery.fromString("SELECT COUNT(*) AS count FROM default WHERE type='user'");
  bucket.query(q, function(err, res) {
    resp.send(res);
  });
});

var server = app.listen(4300, function () {
  var host = server.address().address;
  var port = server.address().port;
  console.log('Example app listening at http://%s:%s', host, port);
});
