import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICheval, getChevalIdentifier } from '../cheval.model';

export type EntityResponseType = HttpResponse<ICheval>;
export type EntityArrayResponseType = HttpResponse<ICheval[]>;

@Injectable({ providedIn: 'root' })
export class ChevalService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/chevals', 'gaf');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(cheval: ICheval): Observable<EntityResponseType> {
    return this.http.post<ICheval>(this.resourceUrl, cheval, { observe: 'response' });
  }

  update(cheval: ICheval): Observable<EntityResponseType> {
    return this.http.put<ICheval>(`${this.resourceUrl}/${getChevalIdentifier(cheval) as number}`, cheval, { observe: 'response' });
  }

  partialUpdate(cheval: ICheval): Observable<EntityResponseType> {
    return this.http.patch<ICheval>(`${this.resourceUrl}/${getChevalIdentifier(cheval) as number}`, cheval, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICheval>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICheval[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addChevalToCollectionIfMissing(chevalCollection: ICheval[], ...chevalsToCheck: (ICheval | null | undefined)[]): ICheval[] {
    const chevals: ICheval[] = chevalsToCheck.filter(isPresent);
    if (chevals.length > 0) {
      const chevalCollectionIdentifiers = chevalCollection.map(chevalItem => getChevalIdentifier(chevalItem)!);
      const chevalsToAdd = chevals.filter(chevalItem => {
        const chevalIdentifier = getChevalIdentifier(chevalItem);
        if (chevalIdentifier == null || chevalCollectionIdentifiers.includes(chevalIdentifier)) {
          return false;
        }
        chevalCollectionIdentifiers.push(chevalIdentifier);
        return true;
      });
      return [...chevalsToAdd, ...chevalCollection];
    }
    return chevalCollection;
  }
}
