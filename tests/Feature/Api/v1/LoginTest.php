<?php

declare(strict_types=1);

namespace Tests\Feature\Api\v1;

use App\Models\User;
use Hash;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Tests\TestCase;

class LoginTest extends TestCase
{
    use RefreshDatabase;

    public static function invalidRequestsProvider(): array
    {
        return [
            'login is missing' => [
                'data' => [
                    'requestBody' => [
                        'password' => 'password',
                    ],
                ],
                'expected' => [
                    'status' => 400,
                    'responseBody' => [
                        'errors' => [
                            'login' => ['The login field is required.'],
                        ],
                    ],
                ],
            ],
            'password is missing' => [
                'data' => [
                    'requestBody' => [
                        'login' => 'login',
                    ],
                ],
                'expected' => [
                    'status' => 400,
                    'responseBody' => [
                        'errors' => [
                            'password' => ['The password field is required.'],
                        ],
                    ],
                ],
            ],
        ];
    }

    /**
     * @dataProvider invalidRequestsProvider
     */
    public function testInvalidRequestsReturnCorrectResponses(array $data, array $expected)
    {
        $response = $this->postJson('/api/v1/login', $data['requestBody']);
        $response
            ->assertStatus($expected['status'])
            ->assertJson($expected['responseBody']);
    }

    public static function nonexistentUserProvider(): array
    {
        return [
            [
                'data' => [
                    'login' => 'does not exist',
                    'requestBody' => [
                        'login' => 'does not exist',
                        'password' => 'password',
                    ],
                ],
                'expected' => [
                    'status' => 401,
                    'responseBody' => [
                        'errors' => [
                            'login' => ['Invalid login or password.'],
                        ],
                    ],
                ],
            ],
        ];
    }

    /**
     * @dataProvider nonexistentUserProvider
     */
    public function testSendingNonexistentLoginReturns401(array $data, array $expected)
    {
        $this->assertDatabaseMissing('users', ['login' => $data['login']]);

        $response = $this->postJson('/api/v1/login', $data['requestBody']);
        $response
            ->assertStatus($expected['status'])
            ->assertJson($expected['responseBody']);
    }

    public static function existingUserProvider(): array
    {
        return [
            'invalid password' => [
                'data' => [
                    'user' => [
                        'login' => 'login',
                        'password' => 'password',
                        'uuid' => '042a5e16-b642-4b6d-b4e2-24a618b2f2ce',
                    ],
                    'requestBody' => [
                        'login' => 'login',
                        'password' => 'invalid password',
                    ],
                ],
                'expected' => [
                    'status' => 401,
                    'responseBody' => [
                        'errors' => [
                            'login' => ['Invalid login or password.'],
                        ],
                    ],
                ],
            ],
            'valid password' => [
                'data' => [
                    'user' => [
                        'login' => 'login',
                        'password' => 'password',
                        'uuid' => '042a5e16-b642-4b6d-b4e2-24a618b2f2ce',
                    ],
                    'requestBody' => [
                        'login' => 'login',
                        'password' => 'password',
                    ],
                ],
                'expected' => [
                    'status' => 200,
                    'responseBody' => [
                        'uuid' => '042a5e16-b642-4b6d-b4e2-24a618b2f2ce',
                    ],
                ],
            ],
        ];
    }

    /**
     * @dataProvider existingUserProvider
     */
    public function testSendingInvalidPasswordReturns401(array $data, array $expected)
    {
        $userData = $data['user'];
        User::create([
            'login' => $userData['login'],
            'password' => Hash::make($userData['password']),
            'uuid' => $userData['uuid'],
        ]);

        $this->assertDatabaseHas('users', ['login' => $userData['login']]);

        $response = $this->postJson('/api/v1/login', $data['requestBody']);
        $response
            ->assertStatus($expected['status'])
            ->assertJson($expected['responseBody']);
    }
}
