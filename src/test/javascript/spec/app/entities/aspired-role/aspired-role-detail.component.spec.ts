import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { AspiredRoleDetailComponent } from 'app/entities/aspired-role/aspired-role-detail.component';
import { AspiredRole } from 'app/shared/model/aspired-role.model';

describe('Component Tests', () => {
  describe('AspiredRole Management Detail Component', () => {
    let comp: AspiredRoleDetailComponent;
    let fixture: ComponentFixture<AspiredRoleDetailComponent>;
    const route = ({ data: of({ aspiredRole: new AspiredRole('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [AspiredRoleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AspiredRoleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AspiredRoleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.aspiredRole).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
