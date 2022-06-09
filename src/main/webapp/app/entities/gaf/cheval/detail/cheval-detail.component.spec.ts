import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ChevalDetailComponent } from './cheval-detail.component';

describe('Cheval Management Detail Component', () => {
  let comp: ChevalDetailComponent;
  let fixture: ComponentFixture<ChevalDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChevalDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ cheval: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ChevalDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ChevalDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load cheval on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.cheval).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
