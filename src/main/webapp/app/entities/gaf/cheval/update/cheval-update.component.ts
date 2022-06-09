import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ICheval, Cheval } from '../cheval.model';
import { ChevalService } from '../service/cheval.service';

@Component({
  selector: 'jhi-cheval-update',
  templateUrl: './cheval-update.component.html',
})
export class ChevalUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    weight: [],
  });

  constructor(protected chevalService: ChevalService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cheval }) => {
      this.updateForm(cheval);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cheval = this.createFromForm();
    if (cheval.id !== undefined) {
      this.subscribeToSaveResponse(this.chevalService.update(cheval));
    } else {
      this.subscribeToSaveResponse(this.chevalService.create(cheval));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICheval>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(cheval: ICheval): void {
    this.editForm.patchValue({
      id: cheval.id,
      name: cheval.name,
      weight: cheval.weight,
    });
  }

  protected createFromForm(): ICheval {
    return {
      ...new Cheval(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      weight: this.editForm.get(['weight'])!.value,
    };
  }
}
