import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICheval } from '../cheval.model';

@Component({
  selector: 'jhi-cheval-detail',
  templateUrl: './cheval-detail.component.html',
})
export class ChevalDetailComponent implements OnInit {
  cheval: ICheval | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cheval }) => {
      this.cheval = cheval;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
