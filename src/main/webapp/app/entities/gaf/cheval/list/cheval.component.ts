import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICheval } from '../cheval.model';
import { ChevalService } from '../service/cheval.service';
import { ChevalDeleteDialogComponent } from '../delete/cheval-delete-dialog.component';

@Component({
  selector: 'jhi-cheval',
  templateUrl: './cheval.component.html',
})
export class ChevalComponent implements OnInit {
  chevals?: ICheval[];
  isLoading = false;

  constructor(protected chevalService: ChevalService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.chevalService.query().subscribe({
      next: (res: HttpResponse<ICheval[]>) => {
        this.isLoading = false;
        this.chevals = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: ICheval): number {
    return item.id!;
  }

  delete(cheval: ICheval): void {
    const modalRef = this.modalService.open(ChevalDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cheval = cheval;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
