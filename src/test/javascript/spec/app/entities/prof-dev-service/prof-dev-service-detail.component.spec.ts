import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { ProfDevServiceDetailComponent } from 'app/entities/prof-dev-service/prof-dev-service-detail.component';
import { ProfDevService } from 'app/shared/model/prof-dev-service.model';

describe('Component Tests', () => {
  describe('ProfDevService Management Detail Component', () => {
    let comp: ProfDevServiceDetailComponent;
    let fixture: ComponentFixture<ProfDevServiceDetailComponent>;
    const route = ({ data: of({ profDevService: new ProfDevService('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [ProfDevServiceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProfDevServiceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProfDevServiceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.profDevService).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
