<searchres>
{{#UserHead}}
<table class="summary">
        <thead>
           <tr>
                  <th>Type of Crime</th>
                  <th>Number of crimes commited</th>
                  <th>Crime Rate</th>
                  
           </tr>
           
         </thead>
{{/UserHead}}
{{#Users}}
<tbody>
                 <tr>
                 <td class="ID">{{_id}} </td>
		<td class="Count">{{count}}</td>
		<td class="percentage">{{Percentage}}</td>
		
               </tr>

		</tbody>
{{/Users}}    
</table>
{{#UserHead1}}
<table class="summary">
        <thead>
           <tr>
                  <th>Type of Crime</th>
                  <th>Number of crimes commited</th>
                  <th>Crime Rate</th>
                  
           </tr>
           
         </thead>
{{/UserHead1}}
{{#Users1}}
<tbody>
                 <tr>
                 <td class="ID">{{_id}} </td>
		<td class="Count">{{count}}</td>
		<td class="percentage">{{Percentage}}</td>
		
               </tr>

		</tbody>
{{/Users1}}    
</table>
{{#UserHead2}}
<table class="summary">
        <thead>
           <tr>
                  <th>ID</th>
                  <th>Case Number</th>
                  <th>Date</th>
                  <th>Block</th>
                  <th>IUCR</th>
                  <th>Primary Type</th>
                  <th>Description</th>
                  <th>Location Description</th>
                  <th>Arrest</th>
                  <th>Domestic</th>
                  <th>Beat</th>
                  <th>District</th>
                  <th>Ward</th>
                  <th>Community Area</th>
                  <th>FBI Code</th>
                  <th>X Coordinate</th>
                  <th>Y Coordinate</th>
                  <th>Year</th>
                  <th>Updated On</th>
                  <th>Latitude</th>
                  <th>Longitude</th>
                  <th>Location</th>
                  
           </tr>
           
         </thead>
{{/UserHead2}}
{{#Users2}}
<tbody>
                 <tr>
                 <td>{{ID}} </td>
		<td>{{Case Number}}</td>
		<td>{{Date}}</td>
		<td>{{Block}} </td>
		<td>{{IUCR}}</td>
		<td>{{Primary Type}}</td>
		<td>{{Description}} </td>
		<td>{{Location Description}}</td>
		<td>{{Arrest}}</td>
		<td>{{Domestic}} </td>
		<td>{{Beat}}</td>
		<td>{{District}}</td>
		<td>{{Ward}} </td>
		<td>{{Community Area}}</td>
		<td>{{FBI Code}}</td>
		<td>{{X Coordinate}} </td>
		<td>{{Y Coordinate}}</td>
		<td>{{Year}}</td>
		<td>{{Updated On}} </td>
		<td>{{Latitude}}</td>
		<td>{{Longitude}}</td>
		<td>{{Location}}</td>
		
               </tr>

		</tbody>
{{/Users2}}    
</table>
</searchres>
