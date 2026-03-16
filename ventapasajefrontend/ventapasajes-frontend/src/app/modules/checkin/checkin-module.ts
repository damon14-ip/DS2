import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'

import { RealizarCheckinComponent } from './realizar-checkin/realizar-checkin'

@NgModule({

imports:[
CommonModule,
RealizarCheckinComponent
],

exports:[
RealizarCheckinComponent
]

})
export class CheckinModule{}