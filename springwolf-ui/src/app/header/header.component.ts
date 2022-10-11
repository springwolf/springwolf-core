import { Component, OnInit } from '@angular/core';
import { AsyncApiService } from 'src/app/shared/asyncapi.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private static URL_SEARCH_DOCUMENT_PARAM = "document"

  documents: string[];
  selectedDocument: string;
  location: Location;

  constructor(private asyncApiService: AsyncApiService) {
    this.location = window.location
    this.selectedDocument = new URLSearchParams(window.location.search).get(HeaderComponent.URL_SEARCH_DOCUMENT_PARAM)
  }

  ngOnInit(): void {
    this.asyncApiService.getAsyncApis().subscribe(docsMap => {
      this.documents = [...docsMap.keys()];
      if(!this.documents.includes(this.selectedDocument)) {
        this.selectedDocument = this.documents[0]
      }
      this.asyncApiService.setCurrentAsyncApiName(this.selectedDocument);
    });
  }

  get selectedDocumentMod() {
    return this.selectedDocument;
  }

  set selectedDocumentMod(value) {
    let updatedSearchParams = new URLSearchParams(window.location.search)
    updatedSearchParams.set(HeaderComponent.URL_SEARCH_DOCUMENT_PARAM, value)
    this.location.search = updatedSearchParams.toString()
  }

}
