'use strict';

const assert = require('assert');
const express = require('express');
const bodyParser = require('body-parser');
const fs = require('fs');
const querystring = require('querystring');

const mustache = require('mustache');
const mongo = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

const app = express()
const port = 3000
const STATIC_DIR = 'statics';
const TEMPLATES_DIR = 'templates';
//app.get('/', (req, res) => res.send('Hello World!'))

app.locals.port = port;
  app.locals.base = "localhost:3000/";
  //app.locals.model = model;
  //app.use('',express.static(STATIC_DIR));
  process.chdir(__dirname);
  app.get('/',function(req,res){
  res.sendFile(__dirname +'/statics' + '/home.html');
  })
  setupTemplates(app);
  setupRoutes(app);
  app.listen(port, function() {
    console.log(`listening on port ${port}`);
  });
  module.export = app;
  function setupRoutes(app) {
  const base = "localhost:3000/";
  app.get(`/search-primary.html`, doSearch(app));
  app.get(`/search-by-location.html`, doSearchLocation(app));
  app.get(`/search-by-Long.html`, doSearchLong(app));
  app.get(`/comp-district.html`, doCompDistrict(app));
  app.get(`/Search-a-case.html`, doSearchCase(app));


}
const FIELDS_INFO = {
	title:{title:'Search By Location Description'},
  Location_Description:{
    friendlyName: 'Location Description',
    isSearch: true,
    isId: true,
    regex: /[\w\-\d\W]+$/,
    error: 'User Id field cannot contain alphanumerics or _',
  }

};
const FIELDS_LONG_LAT = {
	title:{title:'Search BY Longitude and Latitude'},
  
  Latitude:{
    friendlyName: 'Enter Latitude',
    isSearch1: true,
    
    
    
    
  },
  Longitude:{
    friendlyName: 'Enter Longitude',
    isSearch1: true,
    isSearch4:true
    
  },

};

const FIELDS_SEARCH = {
	title:{title:'Search A File'},
  ID:{
    friendlyName: 'ID',
    isSearch2: true,
    
    
  },
  case_Number:{
    friendlyName: 'Case Number',
    isSearch2: true,
    isSearch3:true
    
  },
  
  

};

const FIELDS_DISTRICTS = {
	title:{title:'Compare Crime Rates Between Two Districts'},
  District1:{
    friendlyName: 'Enter First District',
    isSearch: true,
    
    
  },
  District2:{
    friendlyName: 'Enter Second District',
    isSearch: true,
    isSearch5:true
    
  }
  

};
const FIELDS = Object.keys(FIELDS_INFO).map((n) => Object.assign({name: n}, FIELDS_INFO[n]));
const FIELDS1 = Object.keys(FIELDS_LONG_LAT).map((n) => Object.assign({name: n}, FIELDS_LONG_LAT[n]));
const FIELDS2 = Object.keys(FIELDS_DISTRICTS).map((n) => Object.assign({name: n}, FIELDS_DISTRICTS[n]));
const FIELDS3 = Object.keys(FIELDS_SEARCH).map((n) => Object.assign({name: n}, FIELDS_SEARCH[n]));
	
	
function setupTemplates(app) {
  app.templates = {};
  for (let fname of fs.readdirSync(TEMPLATES_DIR)) {
    const m = fname.match(/^([\w\-]+)\.ms$/);
    if (!m) continue;
    try {
      app.templates[m[1]] =
	String(fs.readFileSync(`${TEMPLATES_DIR}/${fname}`));
    }
    catch (e) {
      console.error(`cannot read ${fname}: ${e}`);
      process.exit(1);
    }
  }
}

function doSearchCase(app){
	return async function(req,res){
	const isSubmit = req.query.submit !== undefined;
	const search = getNonEmptyValues(req.query,'SearchAFile');
	console.log(search);
	let id="";
	let caseNo="";
	let query={};
	if(!isSubmit){
	const model = { fields: FIELDS3 };
    	const html = doMustache(app, 'SearchAFile', model);
   	 res.send(html);
   	 }else{
   	 for(let [key,value] of Object.entries(search)){
   	 if(key==="ID"){
   	 if(value!==""){
   	 query.ID=value;
   	 }
   	 }
   	 else if(key==="case_Number"){
   	 if(value!==""){
   	 query["Case Number"]=value;
   	 }
   	 }
   	 }
   	 console.log(id,caseNo);
   	 //let search_param='.*'+string+'*.';
   	const client=await mongo.connect(url,MONGO_OPTIONS);
	const db=client.db("Chicago_Crimes");
	const collection = db.collection("data");
	
	const result = await collection.find(query).limit(10);
	let data=await result.toArray();
	console.log(data);
	await client.close();
	let template = 'SearchAFile';
	
	let model={
	fields: FIELDS3,
	Users2:data,
	UserHead2:1
	
	    
	}
	const html = doMustache(app, template, model);
	res.send(html);
	   	 
	}
	};
};

function doSearch(app) {
  return async function(req, res) {
  const client=await mongo.connect(url,MONGO_OPTIONS);
  const db=client.db("Chicago_Crimes");
  const collection = db.collection("data");
  const result = await collection.aggregate( [ {$group: {_id: "$Primary Type", count: {$sum:1}}}, {$sort: {count: -1}}] );
  const total = await collection.count();
  let total1 = parseInt(total);
  let data=await result.toArray();
  
  console.log(total1);
  let data1 = caclPercentage(data,total1);
  console.log(data);
  await client.close();
  let template = 'search-primary';
	
     let model={
    
    Users:data1,
    UserHead:1,
    
	}
	const html = doMustache(app, template, model);
    res.send(html);
  
  
  };
};
function doSearchLocation(app){
	return async function(req,res){
	const isSubmit = req.query.submit !== undefined;
	const search = getNonEmptyValues(req.query,'SearchByLocation');
	console.log(search);
	let string={"$regex":""};
	if(!isSubmit){
	const model = { fields: FIELDS };
    	const html = doMustache(app, 'searchByLocation', model);
   	 res.send(html);
   	 }else{
   	 for(let [key,value] of Object.entries(search)){
   	 if(key==="Location_Description"){
   	 string["$regex"] = '.*'+value+'*.';
   	 }
   	 }
   	 console.log(string);
   	 //let search_param='.*'+string+'*.';
   	 const client=await mongo.connect(url,MONGO_OPTIONS);
  const db=client.db("Chicago_Crimes");
  const collection = db.collection("data");
  const result = await collection.aggregate([ { $match : { "Location Description" : string} },{$group: {_id: "$Primary Type", count: {$sum:1}}}, {$sort: {count: -1}}, {$limit: 10} ]);
  const total = await collection.count();
  let total1 = parseInt(total);
  let data=await result.toArray();
  
  console.log(total1);
  let data1 = caclPercentage(data,total1);
  console.log(data);
  await client.close();
  let template = 'searchByLocation';
	
     let model={
    fields: FIELDS,
    Users:data1,
    UserHead:1,
    
	}
	const html = doMustache(app, template, model);
    res.send(html);
   	 
   	 }
	}
};

function doSearchLong(app){
	return async function(req,res){
	const isSubmit = req.query.submit !== undefined;
	const search = getNonEmptyValues(req.query,'SearchByLongLat');
	console.log(search);
	let string1={"$regex":""};
	let string2={"$regex":""};
	let value1,value2;
	if(!isSubmit){
	const model = { fields1: FIELDS1};
    	const html = doMustache(app, 'searchByLocation', model);
   	 res.send(html);
   	 }else{
   	 for(let [key,value] of Object.entries(search)){
   	 if(key==="Longitude"){
   	 value2=value;
   	 string1["$regex"] = parseFloat(value).toFixed(2);
   	 }else if(key==="Latitude"){
   	 value1=value;
   	 string2["$regex"] = parseFloat(value).toFixed(2);
   	 }
   	 }
   	 
   	
   	 //let search_param='.*'+string+'*.';
   	 const client=await mongo.connect(url,MONGO_OPTIONS);
  const db=client.db("Chicago_Crimes");
  const collection = db.collection("data");
  const result = await collection.aggregate([ { $match : {"Latitude":{"$regex":parseFloat(value1).toFixed(2)},"Longitude":{"$regex":parseFloat(value2).toFixed(2)}} },{$group: {_id: "$Primary Type", count: {$sum:1}}}, {$sort: {count: -1}} ]);
  const total = await collection.find({"Latitude":{$regex:parseFloat(value1).toFixed(2)},"Longitude":{$regex:parseFloat(value2).toFixed(2)}},{"Primary Type":1,"Longitude":1,"Latitude":1}).count();
  let total1 = parseInt(total);
  let data=await result.toArray();
  
  //console.log(total1);
  let data1 = caclPercentage(data,total1);
  //console.log(data);
  await client.close();
  let template = 'searchByLocation';
	
     let model={
    fields1: FIELDS1,
    Users1:data1,
    UserHead1:1,
    
	}
	const html = doMustache(app, template, model);
    res.send(html);
   	 
   	 }
	
	}; 
};

function doCompDistrict(app){
	return async function(req,res){
	const isSubmit = req.query.submit !== undefined;
	const search = getNonEmptyValues(req.query,'compareDistrict');
	console.log(search);
	let string1={"$regex":""};
	let string2={"$regex":""};
	let value1,value2;
	if(!isSubmit){
	const model = { fields: FIELDS2};
    	const html = doMustache(app, 'compareDistrict', model);
   	 res.send(html);
   	 }else{
   	 for(let [key,value] of Object.entries(search)){
   	 if(key==="District1"){
   	 value1=value;
   	 //string1["$regex"] = parseFloat(value).toFixed(2);
   	 }else if(key==="District2"){
   	 value2=value;
   	 //string2["$regex"] = parseFloat(value).toFixed(2);
   	 }
   	 }
   	 //console.log(value1.toString());
   	 //console.log(value2.toString());
   	 //let search_param='.*'+string+'*.';
   	 const client=await mongo.connect(url,MONGO_OPTIONS);
  const db=client.db("Chicago_Crimes");
  const collection = db.collection("data");
  const result = await collection.aggregate([{$match :{$or:[{"District": value1} , {"District":value2}]}},{$group: {_id: {id:"$Primary Type",District:"$District"}, count: {$sum:1}}}, {$sort: {count: -1}}]);
  //const total = await collection.find({"Longitude":{$regex:parseFloat(value1).toFixed(3)},"Latitude":{$regex:parseFloat(value2).toFixed(3)}},{"Primary Type":1,"Longitude":1,"Latitude":1}).count();
  //let data2 = sortData(result,value1,value2);
  	let data=await result.toArray();
  	let District1=[],District2=[];
  	
	for(let e of data){
	console.log(e);
	if(e._id.District===value1.toString()){
	let data1={};
		data1._id=e._id.id;
		data1["count"]=e.count;
		District1.push(data1);
	}else if(e._id.District===value2.toString()){
		let data2={};
		data2["_id"]=e._id.id;
		data2["count"]=e.count;
		District2.push(data2);
	}
	}
  //let total1 = parseInt(total);
  let total1 = calculateTotal(District1);
  let total2 = calculateTotal(District2);
  
  console.log(District1);
  let dist1 = caclPercentage(District1,total1+total2);
  let dist2 = caclPercentage(District2,total2+total2);
  console.log(District2);
  await client.close();
  let template = 'compareDistrict';
	
     let model={
    fields: FIELDS2,
    Users:dist1,
    Users1:dist2,
    UserHead1:1,
    UserHead:1,
    
	}
	const html = doMustache(app, template, model);
    res.send(html);
   	 
   	 }
	};
};
const FIELDS_INFO_DB={
'SearchByLocation':FIELDS_INFO,
'SearchByLongLat':FIELDS_LONG_LAT,
'compareDistrict':FIELDS_DISTRICTS,
'SearchAFile':FIELDS_SEARCH
}
function calculateTotal(district){
let total=0;
for(let e of district){
total=total+parseInt(e["count"]);
}
return total;
}
function getNonEmptyValues(values,name) {
  const out = {};
//	console.log('entered get non empty value');
	let fields=FIELDS_INFO_DB[name];
//	console.log(fields);
//	if(FIELDS_DB[name]==FIELDS){
  Object.keys(values).forEach(function(k) {
    if (fields[k] !== undefined) {
      const v = values[k];
      if (v && v.trim().length > 0) out[k] = v.trim();
    }
  });

  return out;
}


function caclPercentage(data,total){
 for(let e of data){
 e["Percentage"]=(parseInt(e.count)/total)*100;
 }
 return data;
}
function doMustache(app, templateId, view) {
const templates = { footer: app.templates.footer,searchres:app.templates.searchRes};
  return mustache.render(app.templates[templateId], view,templates);
}
const MONGO_OPTIONS = {
  useNewUrlParser: true,
  useUnifiedTopology: true,
};

