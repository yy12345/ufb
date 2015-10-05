//extend
Array.prototype.unique = function (arr) {
  var ret = [];
  var hash = {};
  for (var i = 0; i < arr.length; i++) {
    var item = arr[i];
    var key = typeof(item) + item;
    if (hash[key] !== 1) {
      ret.push(item);
      hash[key] = 1;
    }
  }
  return ret;
}