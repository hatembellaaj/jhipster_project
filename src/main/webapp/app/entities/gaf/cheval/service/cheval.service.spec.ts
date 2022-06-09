import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICheval, Cheval } from '../cheval.model';

import { ChevalService } from './cheval.service';

describe('Cheval Service', () => {
  let service: ChevalService;
  let httpMock: HttpTestingController;
  let elemDefault: ICheval;
  let expectedResult: ICheval | ICheval[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ChevalService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      name: 'AAAAAAA',
      weight: 0,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Cheval', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Cheval()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Cheval', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          weight: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Cheval', () => {
      const patchObject = Object.assign({}, new Cheval());

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Cheval', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          weight: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Cheval', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addChevalToCollectionIfMissing', () => {
      it('should add a Cheval to an empty array', () => {
        const cheval: ICheval = { id: 123 };
        expectedResult = service.addChevalToCollectionIfMissing([], cheval);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cheval);
      });

      it('should not add a Cheval to an array that contains it', () => {
        const cheval: ICheval = { id: 123 };
        const chevalCollection: ICheval[] = [
          {
            ...cheval,
          },
          { id: 456 },
        ];
        expectedResult = service.addChevalToCollectionIfMissing(chevalCollection, cheval);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Cheval to an array that doesn't contain it", () => {
        const cheval: ICheval = { id: 123 };
        const chevalCollection: ICheval[] = [{ id: 456 }];
        expectedResult = service.addChevalToCollectionIfMissing(chevalCollection, cheval);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cheval);
      });

      it('should add only unique Cheval to an array', () => {
        const chevalArray: ICheval[] = [{ id: 123 }, { id: 456 }, { id: 68311 }];
        const chevalCollection: ICheval[] = [{ id: 123 }];
        expectedResult = service.addChevalToCollectionIfMissing(chevalCollection, ...chevalArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const cheval: ICheval = { id: 123 };
        const cheval2: ICheval = { id: 456 };
        expectedResult = service.addChevalToCollectionIfMissing([], cheval, cheval2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cheval);
        expect(expectedResult).toContain(cheval2);
      });

      it('should accept null and undefined values', () => {
        const cheval: ICheval = { id: 123 };
        expectedResult = service.addChevalToCollectionIfMissing([], null, cheval, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cheval);
      });

      it('should return initial array if no Cheval is added', () => {
        const chevalCollection: ICheval[] = [{ id: 123 }];
        expectedResult = service.addChevalToCollectionIfMissing(chevalCollection, undefined, null);
        expect(expectedResult).toEqual(chevalCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
