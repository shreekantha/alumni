import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { AlumniMeetReqService } from 'app/entities/alumni-meet-req/alumni-meet-req.service';
import { IAlumniMeetReq, AlumniMeetReq } from 'app/shared/model/alumni-meet-req.model';
import { AlumniMeetReqStatus } from 'app/shared/model/enumerations/alumni-meet-req-status.model';

describe('Service Tests', () => {
  describe('AlumniMeetReq Service', () => {
    let injector: TestBed;
    let service: AlumniMeetReqService;
    let httpMock: HttpTestingController;
    let elemDefault: IAlumniMeetReq;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(AlumniMeetReqService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AlumniMeetReq('ID', 'AAAAAAA', 'AAAAAAA', 'image/png', 'AAAAAAA', AlumniMeetReqStatus.REQUESTED);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find('123')
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a AlumniMeetReq', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new AlumniMeetReq(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a AlumniMeetReq', () => {
        const returnedFromService = Object.assign(
          {
            aboutMe: 'BBBBBB',
            description: 'BBBBBB',
            document: 'BBBBBB',
            status: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of AlumniMeetReq', () => {
        const returnedFromService = Object.assign(
          {
            aboutMe: 'BBBBBB',
            description: 'BBBBBB',
            document: 'BBBBBB',
            status: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AlumniMeetReq', () => {
        service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
