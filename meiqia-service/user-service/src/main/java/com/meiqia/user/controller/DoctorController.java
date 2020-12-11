package com.meiqia.user.controller;


import com.meiqia.user.entity.Doctor;
import com.meiqia.user.service.DoctorService;
import com.meiqia.user.struct.PageResult;
import com.meiqia.user.struct.Result;
import com.meiqia.user.vo.SearchVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiusan
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Resource
	private DoctorService doctorService;

    @GetMapping("/{id}")
    public Result<Doctor> getById(@PathVariable("id") Long id) {
        return Result.ok(doctorService.getById(id));
    }


    @GetMapping("/{cpage}/{roomId}")
    public Result<PageResult<Doctor>> findByRid(@PathVariable("cpage") int cpage,@PathVariable("roomId") Long roomId) {

        return Result.ok(doctorService.getByOne(cpage,roomId));
    }

    @GetMapping
    public Result<List<Doctor>> list() {
        return Result.ok(doctorService.list());
    }

    @PostMapping
    public Result add(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody Doctor doctor) {
        return doctorService.updateDoctor(doctor) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return doctorService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<Doctor>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(doctorService.page(currPage, pageSize, vo));
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return doctorService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
