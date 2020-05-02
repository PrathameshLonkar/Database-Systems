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
    
        
    <form method="GET" action="/comp-district.html">

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
         
	{{#isSearch5}}
      <input name="submit" type="submit" value="search" class="control">
      {{/isSearch5}}
      {{/fields}}
    </form>
        </div>
        {{>searchres}}
    {{>footer}}
  </body>
</html>
