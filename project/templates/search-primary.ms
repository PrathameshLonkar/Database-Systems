<searchprimary>
{{#UserHead}}
<table class="summary">
        <thead>
           <tr>
                  <th>Type Of Crime</th>
                  <th>Count of Crime</th>
                  <th>Percentage</th>
                  
           </tr>
           
         </thead>
{{/UserHead}}
{{#Users}}
<tbody>
                 <tr>
                 <td class="tst-manufacturer">{{_id}} </td>
		<td class="tst-sensor-type-id">{{count}}</td>
		<td class="percentage">{{Percentage}}%</td>
               </tr>

		</tbody>
{{/Users}}    
</table>
{{>footer}}
</searchprimary>
