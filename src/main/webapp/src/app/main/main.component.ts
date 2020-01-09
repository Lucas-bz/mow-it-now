import { Component, OnInit} from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Lawn } from '../model/lawn';
import { MainService } from './main.service';
import { Response } from '../model/Response';
import { LawnResponse } from '../model/LawnResponse';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {



  uploadForm: FormGroup;   

  initial: Lawn;
  last: Lawn;
  fileName = '';

  

  constructor(private formBuilder: FormBuilder, private service: MainService) {
    
  }

  ngOnInit() {
    this.uploadForm = this.formBuilder.group({
      file: ['']
    });
  }

  onFileSelect(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.uploadForm.get('file').setValue(file);
      this.fileName = file.name;
      console.log(this.fileName)
    }
  }

  onSubmit() {
    const formData = new FormData();
    formData.append('file', this.uploadForm.get('file').value);
    this.service.loadInitialLawn(formData).subscribe(
      (res: Response) => {
        this.initial = this.createLanw(res.initial);
        this.last = this.createLanw(res.last);
      },
      (err) => console.log(err)
    );
  } 




  reset() {
    
  }

  createLanw(lawnResponse: LawnResponse){
    return new Lawn(lawnResponse.width, lawnResponse.height, lawnResponse
      .mowerSet);
    
  }

}