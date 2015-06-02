var couchbase = require('couchbase');
var express = require('express');
var uuid = require('uuid');
var app = express();

var cluster = new couchbase.Cluster('couchbase://localhost');
var bucket = cluster.openBucket('travel-sample');

app.get('/flightpath/all', function (req, resp) {
  var q = couchbase.N1qlQuery.fromString("SELECT a.next,s.flight,s.utc,r.equipment FROM `travel-sample` AS r UNNEST r.schedule s JOIN `travel-sample` a ON KEYS r.airlineid WHERE r.sourceairport=$1 AND r.destinationairport=$2 ORDER BY s.utc ASC");
  bucket.query(q, [req.query.from, req.query.to], function(err, res) {
    resp.send(res);
  });
});

app.get('/airport/allNames', function(req, resp) {
  var q = couchbase.N1qlQuery.fromString("SELECT airportname FROM `travel-sample` WHERE type = 'airport' ORDER BY airportname ASC")
  bucket.query(q, function(err, res) {
    var airportNames = [];
    for (var i = 0; i < res.length; ++i) {
      airportNames.push(res[i].airportname);
    }
    resp.send(airportNames);
  });
});

app.get('/airport/prefixedBy', function(req, resp) {
  var q = couchbase.N1qlQuery.fromString("SELECT airportname FROM `travel-sample` WHERE type='airport' AND airportname LIKE $1||'%' ORDER BY airportname ASC");
  bucket.query(q, [req.query.prefix], function(err, res) {
    var airportNames = [];
    for (var i = 0; i < res.length; ++i) {
      airportNames.push(res[i].airportname);
    }
    resp.send(airportNames);
  });
});

app.get('/airport/byFaa', function(req, resp) {
  var q = couchbase.N1qlQuery.fromString("SELECT * FROM `travel-sample` WHERE type='airport' AND faa=$1");
  bucket.query(q, [req.query.faa], function(err, res) {
    if (res.length == 0) {
      return resp.send(404);
    }

    resp.send(res[0]['travel-sample']);
  });
});

app.get('/airport/routes', function(req, resp) {
  var q = couchbase.N1qlQuery.fromString("SELECT ARRAY_LENGTH(schedule) AS num_routes, sourceairport AS source, destinationairport AS dest, airline FROM `travel-sample` WHERE type='route' AND airline=$1 ORDER BY source ASC");
  bucket.query(q, [req.query.airline], function(err, res) {
    var destinations = [];
    for (var i = 0; i < res.length; ++i) {
      destinations.push(res[i].source + ' -> ' + res[i].dest + ': ' + res[i].num_routes);
    }
    res.send(destinations);
  });
});

var server = app.listen(4300, function () {
  var host = server.address().address;
  var port = server.address().port;
  console.log('Example app listening at http://%s:%s', host, port);
});
