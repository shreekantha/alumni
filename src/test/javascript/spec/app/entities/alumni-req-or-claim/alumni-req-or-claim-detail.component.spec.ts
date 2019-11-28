import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { AlumniReqOrClaimDetailComponent } from 'app/entities/alumni-req-or-claim/alumni-req-or-claim-detail.component';
import { AlumniReqOrClaim } from 'app/shared/model/alumni-req-or-claim.model';

describe('Component Tests', () => {
  describe('AlumniReqOrClaim Management Detail Component', () => {
    let comp: AlumniReqOrClaimDetailComponent;
    let fixture: ComponentFixture<AlumniReqOrClaimDetailComponent>;
    const route = ({ data: of({ alumniReqOrClaim: new AlumniReqOrClaim('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [AlumniReqOrClaimDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AlumniReqOrClaimDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlumniReqOrClaimDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.alumniReqOrClaim).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
