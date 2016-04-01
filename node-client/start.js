var consul = require('consul')();

// FOLDER 
consul.kv.get({recurse: true, path: 'config/authn-service'}, function(err, result) {
  if (err) throw err;
  for (var idx in result) {
    console.log("Value=[%s]", result[idx].Value);
  }
});

// DOCUMENT 
consul.kv.get('config/node-client/document', function(err, result) {
  if (err) throw err;
  console.log("Key=[%s]", result.Key);
  console.log("Value=[%s]", result.Value);

  var document = JSON.parse(result.Value);
  console.log("Address=[%s]", document.address);
});
