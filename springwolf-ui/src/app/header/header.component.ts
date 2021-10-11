import { Component, OnInit } from '@angular/core';
import { AsyncApiService } from 'src/app/shared/asyncapi.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  documents: string[];
  selectedDocument: string;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.asyncApiService.getAsyncApis().subscribe(docsMap => {
      this.documents = [...docsMap.keys()];
      this.selectedDocument = this.documents[0]
      this.asyncApiService.setCurrentAsyncApiName(this.selectedDocument);
    });
  }

  get selectedDocumentMod() {
    return this.selectedDocument;
  }

  set selectedDocumentMod(value) {
    this.selectedDocument = value;
    this.asyncApiService.setCurrentAsyncApiName(this.selectedDocument);
  }

}
