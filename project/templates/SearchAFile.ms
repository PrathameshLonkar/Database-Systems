<!DOCTYPE html>
<html>
  <head>

    <title>{{title}}</title>
    <link rel="stylesheet" href="/css/style.css">
  </head>
  <body>

    <div class="page">
        {{#fields}}
    <h1 >{{title}}</h1>
        {{/fields}}
    
        
    <form method="GET" action="/Search-a-case.html">

         {{#fields}}
          {{#isSearch2}}
<div>
            <label for="{{friendlyName}}">
            <span class="in-line">{{friendlyName}}:</span>

            <input name="{{name}}" {{#type}}type="{{type}}"{{/type}} value="{{value}}">
    </div>
</label>

            <br/>
            
          {{/isSearch2}}
         {{/fields}}
         
         {{#fields}}
	{{#isSearch3}}
      <input name="submit" type="submit" value="search" class="control">
      {{/isSearch3}}
      {{/fields}}
    </form>
        </div>
        {{>searchres}}
    {{>footer}}
  </body>
</html>
