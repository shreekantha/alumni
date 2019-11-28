import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { AlumniReqOrClaimService } from 'app/entities/alumni-req-or-claim/alumni-req-or-claim.service';
import { IAlumniReqOrClaim, AlumniReqOrClaim } from 'app/shared/model/alumni-req-or-claim.model';
import { ReqOrClaimStatus } from 'app/shared/model/enumerations/req-or-claim-status.model';

describe('Service Tests', () => {
  describe('AlumniReqOrClaim Service', () => {
    let injector: TestBed;
    let service: AlumniReqOrClaimService;
    let httpMock: HttpTestingController;
    let elemDefault: IAlumniReqOrClaim;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(AlumniReqOrClaimService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new AlumniReqOrClaim('ID', 'AAAAAAA', 'AAAAAAA', ReqOrClaimStatus.SUBMITTED, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            requestedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find('123')
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a AlumniReqOrClaim', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            requestedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            requestedDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new AlumniReqOrClaim(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a AlumniReqOrClaim', () => {
        const returnedFromService = Object.assign(
          {
            requestFor: 'BBBBBB',
            reason: 'BBBBBB',
            status: 'BBBBBB',
            requestedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            requestedDate: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of AlumniReqOrClaim', () => {
        const returnedFromService = Object.assign(
          {
            requestFor: 'BBBBBB',
            reason: 'BBBBBB',
            status: 'BBBBBB',
            requestedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            requestedDate: currentDate
          },
          returnedFromService
        );
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

      it('should delete a AlumniReqOrClaim', () => {
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
