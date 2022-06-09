import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'cheval',
        data: { pageTitle: 'gafApp.gafCheval.home.title' },
        loadChildren: () => import('./gaf/cheval/cheval.module').then(m => m.GafChevalModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
