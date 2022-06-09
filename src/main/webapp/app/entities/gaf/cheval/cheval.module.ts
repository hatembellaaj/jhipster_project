import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ChevalComponent } from './list/cheval.component';
import { ChevalDetailComponent } from './detail/cheval-detail.component';
import { ChevalUpdateComponent } from './update/cheval-update.component';
import { ChevalDeleteDialogComponent } from './delete/cheval-delete-dialog.component';
import { ChevalRoutingModule } from './route/cheval-routing.module';

@NgModule({
  imports: [SharedModule, ChevalRoutingModule],
  declarations: [ChevalComponent, ChevalDetailComponent, ChevalUpdateComponent, ChevalDeleteDialogComponent],
  entryComponents: [ChevalDeleteDialogComponent],
})
export class GafChevalModule {}
