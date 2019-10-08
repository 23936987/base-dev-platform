Function.prototype.getName = function(){
    let name =  this.name || this.toString().match(/function\s*([^(]*)\(/)[1];
    name = name.substring(0,1).toLowerCase()+name.substring(1);
    return name;
};