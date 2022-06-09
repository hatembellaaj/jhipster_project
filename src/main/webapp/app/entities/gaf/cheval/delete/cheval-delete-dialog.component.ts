import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICheval } from '../cheval.model';
import { ChevalService } from '../service/cheval.service';

@Component({
  templateUrl: './cheval-delete-dialog.component.html',
})
export class ChevalDeleteDialogComponent {
  cheval?: ICheval;

  constructor(protected chevalService: ChevalService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.chevalService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
