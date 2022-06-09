import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICheval, Cheval } from '../cheval.model';
import { ChevalService } from '../service/cheval.service';

@Injectable({ providedIn: 'root' })
export class ChevalRoutingResolveService implements Resolve<ICheval> {
  constructor(protected service: ChevalService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICheval> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((cheval: HttpResponse<Cheval>) => {
          if (cheval.body) {
            return of(cheval.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Cheval());
  }
}
