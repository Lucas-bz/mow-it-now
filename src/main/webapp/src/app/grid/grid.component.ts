import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Lawn } from '../model/lawn';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit {

  @Input()
  lawn: Lawn;

  @Input()
  name: string;

  colors = ["Blue ", "Red", "Orange", "Violet", "Indigo", "Yellow "];

  constructor() {
  }

  ngOnInit() {
  }


}