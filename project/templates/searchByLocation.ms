<!DOCTYPE html>
<html>
  <head>

    <title>{{title}}</title>
    <link rel="stylesheet" href="/css/style.css">
  </head>
  <body>

    <div class="page">
        {{#fields}}
    <h1 class="tst-sensor-types-search-page">{{title}}</h1>
        {{/fields}}
    
        
    <form method="GET" action="/search-by-location.html">

         {{#fields}}
          {{#isSearch}}
<div>
            <label for="{{friendlyName}}">
            <span class="in-line">{{friendlyName}}:</span>

            <input name="{{name}}" {{#type}}type="{{type}}"{{/type}} value="{{value}}">
    </div>
</label>

            <br/>
            
          {{/isSearch}}
         
	{{#isSearch}}
      <input name="submit" type="submit" value="search" class="control">
      {{/isSearch}}
      {{/fields}}
    </form>
        </div>
        <div class="page">
        {{#fields1}}
    <h1 class="tst-sensor-types-search-page">{{title}}</h1>
    
        {{/fields1}}
    
        
    <form method="GET" action="/search-by-Long.html">

         {{#fields1}}
          {{#isSearch1}}
<div>
            <label for="{{friendlyName}}">
            <span class="in-line">{{friendlyName}}:</span>

            <input name="{{name}}" {{#type}}type="{{type}}"{{/type}} value="{{value}}">
    </div>
</label>

            <br/>
            
          {{/isSearch1}}
         
	{{#isSearch4}}
      <input name="submit" type="submit" value="search" class="control">
      {{/isSearch4}}
      {{/fields1}}
    </form>
        </div>
        {{>searchres}}
    {{>footer}}
  </body>
</html>

