'use strict';

var couchbase = require('couchbase');

// Connect to Couchbase
var cluster = new couchbase.Cluster('couchbase://localhost?fetch_mutation_tokens=true');

// Open the bucket
var bucket = cluster.openBucket('default');

// Insert some user documents
bucket.upsert('d790a968-7eb7-4df8-bd5c-a3e8174d3106', {
  'first_name': 'Laura',
  'last_name': 'Franecki',
  'city': 'Lake Ollie',
  'country': 'Palau'
}, function(err, res) {
  if (err) throw err;

  bucket.upsert('ce0d99d9-4f1d-4d4b-b026-e030ef918ea0', {
    'first_name': 'Carrie',
    'last_name': 'Torphy',
    'city': 'New Adolffurt',
    'country': 'Luxembourg'
  }, function(err, res) {
    if (err) throw err;

    bucket.upsert('ce0d99d9-4f1d-4d4b-b026-e030ef918ea0', {
      'first_name': 'Kraig',
      'last_name': 'Bosco',
      'city': 'North Sheldon',
      'country': 'Mayotte'
    }, function(err, res) {
      if (err) throw err;

      // Query for all our users
      var qs = 'SELECT * FROM `default` ORDER BY first_name, last_name';
      var q = couchbase.N1qlQuery.fromString(qs);
      bucket.query(q, function(err, rows, meta) {
        if (err) throw err;

        for (var i in rows) {
          console.log(rows[i]);
        }

        process.exit(0);
      });

    });
  });
});
