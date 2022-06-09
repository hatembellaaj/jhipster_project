import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ChevalComponent } from '../list/cheval.component';
import { ChevalDetailComponent } from '../detail/cheval-detail.component';
import { ChevalUpdateComponent } from '../update/cheval-update.component';
import { ChevalRoutingResolveService } from './cheval-routing-resolve.service';

const chevalRoute: Routes = [
  {
    path: '',
    component: ChevalComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ChevalDetailComponent,
    resolve: {
      cheval: ChevalRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ChevalUpdateComponent,
    resolve: {
      cheval: ChevalRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ChevalUpdateComponent,
    resolve: {
      cheval: ChevalRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(chevalRoute)],
  exports: [RouterModule],
})
export class ChevalRoutingModule {}
