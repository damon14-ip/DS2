import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { FondoComponent } from './shared/components/fondo/fondo';
@Component({
selector:'app-root',
 
imports: [RouterOutlet, RouterLink, FondoComponent],
templateUrl:'./app.html',
styleUrls:['./app.css']
})
export class App{}
