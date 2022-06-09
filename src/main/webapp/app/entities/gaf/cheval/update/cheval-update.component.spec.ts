import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ChevalService } from '../service/cheval.service';
import { ICheval, Cheval } from '../cheval.model';

import { ChevalUpdateComponent } from './cheval-update.component';

describe('Cheval Management Update Component', () => {
  let comp: ChevalUpdateComponent;
  let fixture: ComponentFixture<ChevalUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let chevalService: ChevalService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ChevalUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ChevalUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ChevalUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    chevalService = TestBed.inject(ChevalService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const cheval: ICheval = { id: 456 };

      activatedRoute.data = of({ cheval });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(cheval));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Cheval>>();
      const cheval = { id: 123 };
      jest.spyOn(chevalService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cheval });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cheval }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(chevalService.update).toHaveBeenCalledWith(cheval);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Cheval>>();
      const cheval = new Cheval();
      jest.spyOn(chevalService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cheval });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cheval }));
      saveSubject.complete();

      // THEN
      expect(chevalService.create).toHaveBeenCalledWith(cheval);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Cheval>>();
      const cheval = { id: 123 };
      jest.spyOn(chevalService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cheval });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(chevalService.update).toHaveBeenCalledWith(cheval);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
