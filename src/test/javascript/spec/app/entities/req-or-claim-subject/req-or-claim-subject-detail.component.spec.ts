import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { ReqOrClaimSubjectDetailComponent } from 'app/entities/req-or-claim-subject/req-or-claim-subject-detail.component';
import { ReqOrClaimSubject } from 'app/shared/model/req-or-claim-subject.model';

describe('Component Tests', () => {
  describe('ReqOrClaimSubject Management Detail Component', () => {
    let comp: ReqOrClaimSubjectDetailComponent;
    let fixture: ComponentFixture<ReqOrClaimSubjectDetailComponent>;
    const route = ({ data: of({ reqOrClaimSubject: new ReqOrClaimSubject('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [ReqOrClaimSubjectDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ReqOrClaimSubjectDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReqOrClaimSubjectDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.reqOrClaimSubject).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
