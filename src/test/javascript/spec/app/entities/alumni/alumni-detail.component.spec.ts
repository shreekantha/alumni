import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { AlumniDetailComponent } from 'app/entities/alumni/alumni-detail.component';
import { Alumni } from 'app/shared/model/alumni.model';

describe('Component Tests', () => {
  describe('Alumni Management Detail Component', () => {
    let comp: AlumniDetailComponent;
    let fixture: ComponentFixture<AlumniDetailComponent>;
    const route = ({ data: of({ alumni: new Alumni('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [AlumniDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AlumniDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlumniDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.alumni).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
